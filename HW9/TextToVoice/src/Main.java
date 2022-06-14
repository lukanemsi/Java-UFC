import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args)
	{
		TextToSpeech tts = new TextToSpeech();
		System.out.println("write your text");
		Scanner scanner = new Scanner(System.in);
		String textInput = scanner.nextLine();
		tts.speak("You said: " + textInput, 2.0f, false, true);
		
		if(textInput.contains("hello") || textInput.contains("hi"))
			tts.speak("Hello back", 2.0f, false, true);
		if(textInput.contains("what is your name"))
			tts.speak("My name is Kevin", 2.0f, false, true);
		tts.speak("I am Robot-assistant in Basic Math, you can Enter MathExpression,"
				+ "Note do not space between operator", 2.0f, false, true);
			
		System.out.println("write Expression");
		textInput = scanner.nextLine();
		int result = 0;
		 if(textInput.contains("+"))
	        {
	            String[] numbers = textInput.split("\\+");
	            result = Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]);
	        }
	        else if(textInput.contains("-"))
	        {
	            String[] numbers = textInput.split("-");
	            result = Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]);
	        }
	        else if(textInput.contains("*"))
	        {
	            String[] numbers = textInput.split("\\*");
	            result = Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]);
	        }
	        else if(textInput.contains("/"))
	        {
	            String[] numbers = textInput.split("/");
	            result = Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]);
	        }
	        else if(textInput.contains("^"))
	        {
	            String[] numbers = textInput.split("\\^");
	            result = (int)Math.pow(Integer.parseInt(numbers[0]),Integer.parseInt(numbers[1]));
	        }
		tts.speak("result of " + textInput+ "is" + result, 2.0f, false, true);
		System.out.println(result);
		
		
		tts.speak("I can count to your given range if you want", 2.0f, false, true);
		int n = 0;
		System.out.println("Input number");
		int range = scanner.nextInt();
		while(n <=  range)
		{
			tts.speak(String.valueOf(n), 2.0f, false, true);
			n++;
		}
		
		
		tts.speak("I can also spell the word", 2.0f, false, true);
		textInput = scanner.next();
		for(int i = 0; i < textInput.length() ; i++)
		{
			tts.speak(String.valueOf(textInput.charAt(i)), 2.0f, false, true);
		}
		
		textInput = scanner.nextLine();
		List<String> listOfWords = List.of(textInput.split(" "));
		Iterator<String> iter = listOfWords.iterator();
		while(iter.hasNext())
		{
			switch(iter.next())
			{
				case "bye":
					tts.speak("Bye nice to meet you", 2.0f, false, true);
					break;
				case "Thank you":
					tts.speak("Nice!", 2.0f, false, true);
					break;
			}
		}
		
		
		
	 
	}
}
