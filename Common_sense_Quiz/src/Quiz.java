import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends ArrayList<QuizDate> {
    Quiz() {
        FileInputStream fin;
        BufferedReader br;
        try {
            fin = new FileInputStream("QuizSource.txt");
            br = new BufferedReader(new InputStreamReader(fin, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                QuizDate quizDate = new QuizDate(line, br.readLine(), br.readLine(), br.readLine());
                this.add(quizDate);
            }
            System.out.println();
            Collections.shuffle(this);
            fin.close();
            br.close();
        } catch (IOException e) {
            System.out.println("소스 파일 오류");
        }
    }
}
