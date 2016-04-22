import java.math.*;
import java.security.*;
import java.util.Random;
public class Main {
	final static char[] CHARACTERS= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
		'0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','^','&','*','(',')','{','}','[',']',';'};
	
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
		return hash.substring(0, 4);
	}
	
	
	public static void findCollusion(String input) throws NoSuchAlgorithmException
	{
		int counter = 0+(int)'A';
		Random r = new Random();
		StringBuffer randomWord = new StringBuffer();
		randomWord.append(input);
		
		randomWord.append(Main.CHARACTERS[r.nextInt(Main.CHARACTERS.length)]);
		while(!Main.SSHA1(input).equals(Main.SSHA1(randomWord.toString())))
		{
			if(randomWord.length()>10)
			{
				randomWord.delete(0, randomWord.length());
				randomWord.append(input);
			}
			randomWord.append(Main.CHARACTERS[r.nextInt(Main.CHARACTERS.length)]);
			
		}
	
		
		
		System.out.println(randomWord.toString());
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		Main.findCollusion("sl820");
		
	}

}
