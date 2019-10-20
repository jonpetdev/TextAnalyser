package text.domain;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GenServiceImpl implements GenService {


    @Override
    public List<String> list(String text) {
        List<String> list = new ArrayList<>();
        String[] textArray1 = text.split(" ");
        //isrenka lotynisko alfabeto zodzius
        List<String> alfabetic = new ArrayList<>();
        for (int i = 0; i < textArray1.length; i++) {
            if (textArray1[i].matches("[a-zA-Z]*$")) {
                alfabetic.add(textArray1[i]);
            }
        }

        //isrenka visu zodziu paskutines raides
        List<String> raid = new ArrayList<>();
        String raide = "";
        for (int i = 0; i < alfabetic.size(); i++) {
            raide = alfabetic.get(i).substring(alfabetic.get(i).length() - 1);
            raid.add(raide.toLowerCase());
        }

        //suskaiciuoja kiek pasikartojanciu raidziu
        Map<String, Integer> mapas = new HashMap<>();
        for (int i = 0; i < raid.size(); i++) {
            if (mapas.containsKey(raid.get(i))) {
                int count = mapas.get(raid.get(i));
                count++;
                mapas.put(raid.get(i), count);
            } else {
                mapas.put(raid.get(i), 1);
            }
        }

        List<String> pasikartoimai = new ArrayList<>();
        for (String key : mapas.keySet()) {
            pasikartoimai.add(key);
        }

        // priskiria prie priskaiciuotu raidziu zodzius.
        for (int j = 0; j < pasikartoimai.size(); j++) {
            String vardas = pasikartoimai.get(j) + " " + mapas.get(pasikartoimai.get(j));
            for (int i = 0; i < alfabetic.size(); i++) {
                String last = alfabetic.get(i).substring(alfabetic.get(i).length() - 1);
                if (last.equalsIgnoreCase(pasikartoimai.get(j))) {
                    vardas = vardas + " " + alfabetic.get(i);
                }
            }
            list.add(vardas);
        }
        //surusiuoja pagal alfabeta
        List<String> sortedNames = list.stream().sorted().collect(Collectors.toList());
        return sortedNames;
    }

    @Override
    public void writeToFileCurrent(List<List<String>> list, HttpServletRequest request) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        try {
            //sukuria res/ folderi
            new File(rootDirectory + "res/").mkdir();
            FileWriter fw = new FileWriter(rootDirectory + "res/fileCurrent.txt");
            fw.write("");
            for (int i = 0; i < list.size(); i++) {
                fw.append("--------------" + LocalDate.now() + "-" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "----------------;\n");
                for (int j = 0; j < list.get(i).size(); j++) {
                    fw.append(list.get(i).get(j) + ";\n");
                }
            }
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void writeToFileAudit(List<List<String>> list, HttpServletRequest request) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        try {
            new File(rootDirectory + "res/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(rootDirectory + "res/auditas.txt", true));
            for (int i = 0; i < list.size(); i++) {
                writer.append("-------------" + LocalDate.now() + "-" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + "---------------;\n");
                for (int j = 0; j < list.get(i).size(); j++) {
                    writer.append(list.get(i).get(j) + ";\n");
                }
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void multipartFile(HttpServletRequest request, MultipartFile file) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String filename = file.getOriginalFilename();
        try {
            new File(rootDirectory + "res/").mkdir();
            byte barr[] = file.getBytes();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(rootDirectory + "res/" + filename));
            bout.write(barr);
            bout.flush();
            bout.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public LinkedList<String> fileViewList(String content) {
        LinkedList<String> list = new LinkedList<>();
        String[] ar = content.split(";");
        for (String st : ar) {
            list.add(st);
        }
        return list;
    }

    @Override
    public String readFromUpload(HttpServletRequest request, MultipartFile fileN) {
        String rootDirectory = request.getSession().getServletContext().getRealPath("/");
        String filename = fileN.getOriginalFilename();
        File file = new File(rootDirectory + "res/" + filename);
        String content = null;
        try {
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;

    }

}
