package file.handler;

import java.io.File;

/*
* @author yuweixiong
* @date 2020/06/10
* 文件名处理器
*/
public interface FileNameHandler {

    default String handler(String fileName) {
        return fileName;
    };

    default String handler(File fileName) {
        return fileName.getName();
    }
}
