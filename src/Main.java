import java.math.*;
import java.security.*;
import java.util.Random;
public class Main {
	final static char[] CHARACTERS= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';'};
	final static int MAX_LENGTH_OF_INPUT = 100;
	final static int MAX_CHARACTER_LENGTH = 8;
	
	public static String SHA1(String input) throws NoSuchAlgorithmException
	{
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer stringBuffer = new StringBuffer();
		
		for(int i = 0; i < result.length; i++)
		{
			stringBuffer.append(Integer.toString((result[i] & 0xff)+0x100,16).substring(1));
			
		}
		return stringBuffer.toString();
	}
	
	
	public static String SSHA1(String input) throws NoSuchAlgorithmException
	{
		String hash = SHA1(input);
		return hash.substring(0, MAX_CHARACTER_LENGTH);
	}
	
	public static boolean isBothStringEqual(String stringOne, String stringTwo)
	{
		return stringOne.equals(stringTwo);
	}
	
	public static void printResultInfo(String input)
	{
		System.out.println();
		System.out.println("The first input is " + input);
		try {
			System.out.println("The hash of the input using SHA1 function is " + Main.SHA1(input));
			System.out.println("The hash of the input using SSHA1 function is " + Main.SSHA1(input));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void findCollusion(String input) throws NoSuchAlgorithmException
	{	
		Random r = new Random();
		StringBuffer randomWord = new StringBuffer();
		randomWord.append(input);
		
		//to add the first random character so that the first comparision isn't the same input
		randomWord.append(Main.CHARACTERS[r.nextInt(Main.CHARACTERS.length)]);
		
		while(!isBothStringEqual(Main.SSHA1(input),Main.SSHA1(randomWord.toString())))
		{
			if(randomWord.length()>MAX_LENGTH_OF_INPUT)
			{
				randomWord.delete(0, randomWord.length());
				randomWord.append(input);
			}
			randomWord.append(Main.CHARACTERS[r.nextInt(Main.CHARACTERS.length)]);
			
		}
		Main.printResultInfo(input);
		Main.printResultInfo(randomWord.toString());
	
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		Main.findCollusion(Keyboard.readString("Please input the string to find the collusion: "));
		
	}

}
