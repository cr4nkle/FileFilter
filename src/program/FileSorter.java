package program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSorter {
    private HashMap<String, File> fileMap;
    private String toDir;
    private final String regex = "^delta.*";

    public FileSorter(String toDir){
        this.toDir = toDir;
        this.setFileMap(toDir);
    }

    private void setFileMap(String toDir) {
        fileMap = new HashMap<>();
        fileMap.put("dll", new File(toDir + "\\" + "libs"));
        fileMap.put("jar", new File(toDir + "\\" + "plugins"));
        fileMap.put("xlsx", new File(toDir + "\\" + "reports"));
        fileMap.put("bat", new File(toDir + "\\" + "utils"));
    }

    public void getFiles(File f) throws NoSuchFileException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        File adminDir = new File(toDir + "\\" + "Настройки администратора");
        File newDir = null;
        File copied = null;

        if (!adminDir.exists())
            adminDir.mkdir();

        if (f.isDirectory()) {
            File[] listOfFile = f.listFiles();

            for (File file : listOfFile) {
                if (file.isDirectory()) {
                    getFiles(file);
                } else {
                    String ext = getExtension(file);
                    String originFilename = file.getName();
                    Path originPath = file.toPath();

                    if (fileMap.containsKey(ext)){
                        newDir = fileMap.get(ext);
                        if (!newDir.exists())
                            newDir.mkdir();
                        try{
                            matcher = pattern.matcher(originFilename);

                            if (!matcher.matches()){
                                copied = new File(newDir.getPath() + "\\" + originFilename);
                            }else {
                                copied = new File(adminDir.getPath() + "\\" + originFilename);
                            }

                            Files.copy(originPath, copied.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public String getExtension(File path){
        String filename = path.getPath();
        int i = filename.lastIndexOf('.');
        if ((i > 0) && (i < filename.length()-1)) {
            return filename.substring(i+1).toLowerCase();
        }
        return "";
    }
}
