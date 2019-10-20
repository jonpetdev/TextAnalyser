package text.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

public interface GenService {

    List<String> list(String text);

    void writeToFileCurrent(List<List<String>> listas, HttpServletRequest request);

    void writeToFileAudit(List<List<String>> listas, HttpServletRequest request);

    void multipartFile(HttpServletRequest request, MultipartFile file);

    LinkedList<String> fileViewList(String content);

    String readFromUpload(HttpServletRequest request, MultipartFile fileN);
}
