
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
//CREATION OF A CLASS CALLED QUESTION AND ADDING TEXT, POSSIBLE ANSWERS AND CORRECT ANSWERS
public class Question  {
	private String questionText;
	private String[] choices;
	private int correctAnswer;
	private JFileChooser fc;
	

	public Question(String text, String[] choices, int correctAnswer) {

		this.questionText = text;
		this.choices = choices;
		this.correctAnswer = correctAnswer;
	}
//RETURNING CORRECT CHOICES, CORRECT OPTION AND THE QUESTIONS FROM QUESTION.TXT FILEF
	public Question() {
		}
    public String getQuestionText() {
		return questionText;
	}
    public String[] getChoices() {
		return choices;
	}
    public int getCorrectAnswer() {
		return correctAnswer;
	}
//THE IF LOOP FOR CHOOSING THE OPTION
    //LOADQUESTIONFROMFILE IS USED TO BRING IN THE QUESTIONS FROM QUESTION.TXT FILE
	public List<Question> loadQuestionsFromFile(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        List<Question> questions = new ArrayList<>();
        
        int dialogResult = fileChooser.showOpenDialog(frame);
        if (dialogResult != JFileChooser.APPROVE_OPTION) {
            return questions;
        }
//TRY AND CATCH ERROR FOR CATCHING THE READING FILE ERROR EXCEPTION
        File selectedFile = fileChooser.getSelectedFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
            String questionText = reader.readLine();
            while (questionText != null) {
                String[] choices = new String[4];
                for (int i = 0; i < 4; i++) {
                    choices[i] = reader.readLine();
                }
                int correctAnswerIndex = Integer.parseInt(reader.readLine());
                Question question = new Question(questionText, choices, correctAnswerIndex);
                questions.add(question);
                questionText = reader.readLine(); 
            }
//THIS THE ERROR EXCEPTION 
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
        
        return questions;
    }

}
