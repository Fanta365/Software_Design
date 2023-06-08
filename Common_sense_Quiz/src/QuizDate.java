public class QuizDate {
    String quiz;
    String answer;
    String hint;
    String explanation;

    QuizDate(String quiz, String answer, String hint, String explanation) {
        this.quiz = quiz;
        this.answer = answer;
        this.hint = hint;
        this.explanation = explanation;

    }

    String getQuiz() {
        return quiz;
    }

    String getAnswer() {
        return answer;
    }

    String getHint() {
        return hint;
    }

    String getExplanation() {
        return explanation;
    }
}
