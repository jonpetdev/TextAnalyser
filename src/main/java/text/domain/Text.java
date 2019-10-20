
package text.domain;


import org.springframework.web.multipart.MultipartFile;

public class Text {
    private String textas1;
    private String textas2;
    private MultipartFile file;


    public String getTextas1() {
        return textas1;
    }

    public void setTextas1(String textas1) {
        this.textas1 = textas1;
    }

    public String getTextas2() {
        return textas2;
    }

    public void setTextas2(String textas2) {
        this.textas2 = textas2;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
