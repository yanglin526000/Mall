package com.mall.file.utils;

import com.mall.file.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * <p>
 * FastDFSUtils
 * </p>
 *
 * @author yanglin
 * @date 2020-06-29 23:56:50
 */
public class FastDFSUtils {

    static {
        String path = new ClassPathResource("fdfs_client.conf").getPath();
        try {
            ClientGlobal.init(path);
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     * downFile
     * </p>
     *
     * @param groupName
     * @param remoteFileName
     * @return java.io.InputStream
     * @author yanglin
     * @date 2020-06-29 23:58:05
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            TrackerServer trackerServer = getTrackerServer();
            StorageClient storageClient = getStorageClient(trackerServer);

            //参数1:指定组名
            //参数2 :指定远程的文件名
            byte[] bytes = storageClient.download_file(groupName, remoteFileName);
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            return byteArrayInputStream;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static TrackerServer getTrackerServer() throws Exception {
        return new TrackerClient().getConnection();
    }

    public static StorageClient getStorageClient(TrackerServer trackerServer) throws Exception {
        return new StorageClient(trackerServer, null);
    }

    /**
     * <p>
     * Delete File
     * </p>
     *
     * @param groupName
     * @param remoteFileName
     * @author yanglin
     * @date 2020-06-30 09:44:20
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        try {
            TrackerServer trackerServer = getTrackerServer();
            StorageClient storageClient = getStorageClient(trackerServer);
            int i = storageClient.delete_file(groupName, remoteFileName);
            if (i == 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 文件上传
     *
     * @param fastDFSFile 上传的文件信息
     * @return
     */
    public static String[] upload(FastDFSFile fastDFSFile) {
        //附加参数
        NameValuePair[] metaList = new NameValuePair[1];
        metaList[0] = new NameValuePair("author", fastDFSFile.getAuthor());

        TrackerServer trackerServer = null;
        StorageClient storageClient = null;
        String[] upload = null;
        try {
            trackerServer = getTrackerServer();
            storageClient = getStorageClient(trackerServer);
            /**
             * 通过 Storageclient访问 Storage,实现文件上传,并且获取文件上传后的存储信息
             * 1：上传的字节数组
             * 2：文件的扩展名
             * 3：附加参数
             */
            upload = storageClient.upload_appender_file(fastDFSFile.getContent(), fastDFSFile.getExt(), metaList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return upload;
    }

    /**
     * <p>
     * Get Storage Information By Group Name
     * </p>
     *
     * @param groupName
     * @return org.csource.fastdfs.StorageServer
     * @author yanglin
     * @date 2020-06-30 09:46:30
     */
    public static StorageServer getStorages(String groupName) {
        try {
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            //参数1 指定traqckerserver 对象
            //参数2 指定组名
            StorageServer group1 = trackerClient.getStoreStorage(trackerServer, groupName);
            return group1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * Get File Information By Group Name And Remote File Name
     * </p>
     *
     * @param groupName
     * @param remoteFileName
     * @return org.csource.fastdfs.FileInfo
     * @author yanglin
     * @date 2020-06-30 09:47:50
     */
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            TrackerServer trackerServer = getTrackerServer();
            StorageClient storageClient = getStorageClient(trackerServer);
            //参数1 指定组名
            //参数2 指定文件的路径
            FileInfo fileInfo = storageClient.get_file_info(groupName, remoteFileName);
            return fileInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * Get Server Info By Group Name And Remote File Name
     * </p>
     *
     * @param groupName
     * @param remoteFileName
     * @return org.csource.fastdfs.ServerInfo[]
     * @author yanglin
     * @date 2020-06-30 09:49:04
     */
    public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) {
        try {
            //3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();

            ServerInfo[] group1s = trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
            return group1s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * <p>
     * Get Tracker Url
     * </p>
     *
     * @return java.lang.String
     * @author yanglin
     * @date 2020-06-30 09:50:15
     */
    public static String getTrackerUrl() {
        try {
            //3.创建trackerclient对象
            TrackerClient trackerClient = new TrackerClient();
            //4.创建trackerserver 对象
            TrackerServer trackerServer = trackerClient.getConnection();
            //tracker 的ip的信息
            String hostString = trackerServer.getInetSocketAddress().getHostString();

            int trackerHttpPort = ClientGlobal.getG_tracker_http_port();
            return "http://" + hostString + ":" + trackerHttpPort;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
