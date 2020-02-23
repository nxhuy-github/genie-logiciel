package fr.univ_lyon1.info.m1;

public class CharManipulator implements ICharManipulator{

	public String invertOrder(String s) {
		StringBuilder str = new StringBuilder(s).reverse();
		return str.toString();
	}

	public String invertCase(String s) {
		StringBuilder str = new StringBuilder(s);
		for(int i = 0 ; i < str.length(); i++){
			char c = str.charAt(i);
			if(Character.isLowerCase(c)){
				str.setCharAt(i, Character.toUpperCase(c));
			}else{
				str.setCharAt(i, Character.toLowerCase(c));
			}
		}
		return str.toString();
	}

	public String removePattern(String s, String pattern) {
		if(s.contains(pattern)){
			String str = s.replace(pattern, "");
			return s;
		}else{
			return s;	
		}
		
	}

}
