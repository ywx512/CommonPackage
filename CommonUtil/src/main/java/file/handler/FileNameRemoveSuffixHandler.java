package file.handler;

import java.io.File;

/*
* @author yuweixiong
* @date 2020/06/10
* 删除文件后缀名 文件名处理器
*/
public class FileNameRemoveSuffixHandler implements FileNameHandler {

    @Override
    public String handler(String fileName) {
        return removeFileNameSuffix(fileName);
    }

    @Override
    public String handler(File fileName) {
        return removeFileNameSuffix(fileName.getName());
    }

    private String removeFileNameSuffix(String fileName) {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(0, pos);
    }
}
