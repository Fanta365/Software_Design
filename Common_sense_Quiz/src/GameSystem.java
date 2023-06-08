import java.io.IOException;
import java.util.Scanner;

public class GameSystem {
    int correctCount;
    int quizTotalNumber = 5;
    Quiz quiz;
    int totalChance = 3;
    int passCount;
    int totalHint = 3;
    boolean startSignal = false;

    GameSystem() {
        quiz = new Quiz();
    }

    void run() {
        System.out.println("Common sense Quiz 시작합니다. 메뉴를 고르세요");
        while (!startSignal) {
            System.out.println("1.게임 시작, 2.총 문제 수 정하기, 3.문제 별 시도 횟수 정하기, 4.총 힌트 횟수 정하기, 5.퀴즈 파일 보기");
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            switch (n) {
                case 1: {
                    gameStart();
                    startSignal = true;
                    break;
                }
                case 2: {
                    setQuizTotalNumber();
                    break;
                }
                case 3: {
                    setTotalChance();
                    break;
                }
                case 4: {
                    setTotalHint();
                    break;
                }
                case 5: {
                    viewQuizSource();
                    break;
                }

            }
        }
    }

    void setTotalHint() {
        System.out.println("게임에서 총 힌트 횟수를 입력하세요 (현재 총 힌트 횟수는 " + totalHint +"회)");
        Scanner scanner = new Scanner(System.in);
        totalHint = scanner.nextInt();
    }

    void setTotalChance() {
        System.out.println("문제당 풀 기회를 입력하세요 (현재 문제당 기회는 " + totalChance + "회)");
        Scanner scanner = new Scanner(System.in);
        totalChance = scanner.nextInt();
    }

    void setQuizTotalNumber() {
        System.out.println("총 문제의 수를 입력하세요 (현재 총 문제수는 " + quizTotalNumber + " 최대치는 " + quiz.size() + ")");
        Scanner scanner = new Scanner(System.in);
        quizTotalNumber = scanner.nextInt();
        if (quizTotalNumber > quiz.size())
            quizTotalNumber = quiz.size();
    }

    void gameStart() {
        System.out.println();
        System.out.println("퀴즈를 시작합니다.");
        System.out.println("(힌트를 사용하려면, 문제 푸는중 '힌트' 입력 | 문제를 패스하려면, 문제 푸는중 '패스' 입력)");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < quizTotalNumber; i++) {
            QuizDate quizDate = quiz.get(i);
            String quiz = quizDate.getQuiz();
            String answer = quizDate.getAnswer();
            System.out.println((i + 1) + ")" + quiz + " [기회는 총 " + totalChance + "번]");
            int chance = 0;
            while (chance < totalChance) {
                String userAnswer = scanner.nextLine();
                if (userAnswer.equals("패스")) {
                    passCount++;
                    break;
                }
                if (userAnswer.equals("힌트")) {
                    if (totalHint > 0) {
                        System.out.println(quizDate.getHint());
                        totalHint--;
                        continue;
                    } else if (totalHint == 0)
                        System.out.println("남은 힌트 횟수가 없습니다.");
                    continue;
                }
                if (answer.equals(userAnswer)) {
                    System.out.println("정답입니다!");
                    correctCount++;
                    break;
                } else {
                    System.out.println("틀렸습니다.");
                    chance++;
                }
            }
            System.out.println();
            System.out.println("답과 관련한 정보를 보시겠어요? (설명을 보려면 y 입력)");
            String explanation = scanner.nextLine();
            if (explanation.equals("y"))
                System.out.println(quizDate.getExplanation());
            System.out.println();
        }
        System.out.println("남은 문제가 없습니다.");
        quiz.clear();
        System.out.println();
        viewResult();
        scanner.nextLine();
        scanner.nextLine();
    }

    void viewQuizSource() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("C:\\Windows\\System32\\notepad.exe .\\QuizSource.txt");
        } catch (IOException e) {
            System.out.println("퀴즈 소스 파일 오류");
        }

    }

    void viewResult() {
        System.out.println("===== 게임 결과 =====");
        System.out.println("문제 수 : " + quizTotalNumber);
        System.out.println("패스 수 : " + passCount);
        System.out.println("정답 수 : " + correctCount);
        System.out.println("남은 힌트 수 : " + totalHint);
        if ((passCount - correctCount) > 0)
            System.out.println("점수 : 패스수가 너무 많네요! ");
        else
            System.out.println("점수 : " + quizTotalNumber + " / " + (correctCount - passCount));
        System.out.println("===================");
    }
}