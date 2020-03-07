package com.ht;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mbql
 * @date 2020/3/7 17:33
 */
public class UploadImage {
    private static Logger logger = LoggerFactory.getLogger(UploadImage.class);

    @Test
    public void testUpload() {
        try {
            //初始化配置文件
            ClientGlobal.initByProperties("fastdfs-client.properties");
            //获取TrackerClient()客户端对象
            TrackerClient tracker = new TrackerClient();
            //获取该连接
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;
            //创建数据存储对象
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);
            //该对象保存数据的元信息，有的话可以键值对的方式写入，没有可以为null
            NameValuePair nvp[] = null;
            //上传到文件系统
            String fileId = client.upload_file1("C:\\Users\\HP\\Pictures\\Camera Roll\\timg.jpg", "jpg",
                    nvp);
            logger.info(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
