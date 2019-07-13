package com.springboot.framework.service.impl;

import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.ObjectMetadata;
import com.springboot.framework.config.COSConfig;
import com.springboot.framework.constant.Errors;
import com.springboot.framework.service.COSService;
import com.springboot.framework.util.ExceptionUtil;
import com.springboot.framework.util.FileUtil;
import com.springboot.framework.util.OSSContentTypeUtil;
import com.springboot.framework.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/13 23:25
 */
@Service
public class COSServiceImpl implements COSService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private COSConfig cosConfig;
    @Resource
    private COSClient uploadClient;
    @Resource
    private COSClient downloadClient;

    /**
     * oss上传文件，返回文件访问路径
     *
     * @param file：文件
     * @return String
     */
    @Override
    public String upload(MultipartFile file) {
        String originFileName = file.getOriginalFilename();
        String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
        String fileType = OSSContentTypeUtil.getContentType(suffixName);
        // 设置文件名
        String filePathName = generateRelativeStoragePath(suffixName);
        byte[] fileContent = null;
        try {
            fileContent = file.getBytes();
        } catch (Exception e) {
            logger.error("Cannot get file content from {}.", originFileName);
            // ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "不能读取" + originFileName + "内容");
        }
        ObjectMetadata meta = new ObjectMetadata();
        // 设置上传文件长度
        meta.setContentLength(file.getSize());

        // 设置上传MD5校验
        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
        meta.setContentMD5(md5);
        meta.setContentType(fileType);

        // 存储
        try {
            uploadClient.putObject(cosConfig.getBucketName(), filePathName, file.getInputStream(), meta);
        } catch (Exception e) {
            logger.error("COS storage error", e);
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "COS storage exception");
        }
//        String path = cosConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
        String path = cosConfig.getDownloadEndpoint() + "/" + filePathName;
        if (FileUtil.isImg(suffixName)) {
            // 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
            path += StringUtil.isNotBlank(cosConfig.getStyleName()) ? "?x-image-process=image/" + cosConfig.getStyleName() : "";
        }
        return path;
    }

    /**
     * base64code方式上传
     *
     * @param imageBytes 文件流
     * @return String
     */
    @Override
    public String uploadImageBase64(byte[] imageBytes) {
        String fileType = "image/jpeg";
        // 设置文件名
        String filePathName = generateRelativeStoragePath("jpeg");
        ObjectMetadata meta = new ObjectMetadata();
        // 设置上传文件长度
        meta.setContentLength((long) imageBytes.length);
        // 设置上传MD5校验
        String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(imageBytes));
        meta.setContentMD5(md5);
        meta.setContentType(fileType);

        // 存储
        try {
            uploadClient.putObject(cosConfig.getBucketName(), filePathName, new ByteArrayInputStream(imageBytes), meta);
        } catch (Exception e) {
            logger.error("OBS storage error", e);
            //ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "BOS storage exception");
        }
        String path = cosConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
        // 图片访问处理样式，可在oss自定义,缩放、裁剪、压缩、旋转、格式、锐化、水印等
        return path + (StringUtil.isNotBlank(cosConfig.getStyleName()) ? "?x-oss-process=style/" + cosConfig.getStyleName() : "");
    }

    /**
     * File方式上传
     *
     * @param file 文件
     * @return String
     */
    @Override
    public String uploadFile(File file) {
        // 存储
        InputStream is = null;
        try {
            String originFileName = file.getName();
            String suffixName = originFileName.substring(originFileName.indexOf(".") + 1);
            String fileType = OSSContentTypeUtil.getContentType(suffixName);
            // String fileType = "application/octet-stream";
            // 设置文件名
            String filePathName = generateRelativeStoragePath(suffixName);
            is = new FileInputStream(file);
            byte[] fileContent = new byte[is.available()];
            is.read(fileContent);
            ObjectMetadata meta = new ObjectMetadata();
            // 设置上传文件长度
            meta.setContentLength(file.length());
            // 设置上传MD5校验
            String md5 = BinaryUtil.toBase64String(BinaryUtil.calculateMd5(fileContent));
            meta.setContentMD5(md5);
            meta.setContentType(fileType);
            uploadClient.putObject(cosConfig.getBucketName(), filePathName, new ByteArrayInputStream(fileContent), meta);
            String path = cosConfig.getDownloadEndpoint() + FileUtil.getFileSeparator() + filePathName;
            return path;
        } catch (Exception e) {
            logger.error("OBS storage error", e);
            //ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "OSS storage exception");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 下载文件
     *
     * @param url 路径
     * @return String
     */
    @Override
    public byte[] download(String url) {
        InputStream is = null;
        try {
            String key = url.split(cosConfig.getDownloadEndpoint() + "/")[1];
            COSObject bosObject = downloadClient.getObject(cosConfig.getBucketName(), key);
            is = bosObject.getObjectContent();
            byte[] data = IOUtils.readStreamAsByteArray(is);
            return data;
        } catch (Exception e) {
            logger.error("下载文件异常,url={}", url, e);
            e.printStackTrace();
            // ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "下载文件异常");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


    /**
     * 获取存储的相对路径 规则path + / + yyyyMMddHH + uuid
     *
     * @param suffixName 后缀名
     * @return String
     */
    private String generateRelativeStoragePath(String suffixName) {
        SimpleDateFormat yyyyMMddHH = new SimpleDateFormat("yyyyMMddHH");
        String time = yyyyMMddHH.format(new Date());
        String uuid = StringUtil.getUUID(8);
        StringBuilder sb = new StringBuilder();
        String storagePath = this.cosConfig.getStoragePath();
        if (StringUtil.isNotBlank(storagePath)) {
            sb.append(storagePath).append("/");
        }
        sb.append(time).append(uuid);
        if (StringUtil.isNotBlank(suffixName)) {
            sb.append(".").append(suffixName);
        }
        return sb.toString();
    }

}