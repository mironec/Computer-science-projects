package cs.programs;

import cs.CSProgram;

public class Tries extends CSProgram {

	public Tries(int id) {
		super("Tries", id);
	}

	private class Trie {
		Trie alphabet[];
		String end;
		
		public Trie(){
			alphabet=new Trie[26];
			end=null;
		}
		
		public void setEnd(String s){
			end = s;
		}
		
		public void addString(String s, String end){
			if(s.isEmpty()){
				setEnd(end);
				return;
			}
			char c = s.toLowerCase().charAt(0);
			Trie curr = alphabet[c-'a'];
			if(curr==null){
				alphabet[c-'a']=new Trie();
			}
			alphabet[c-'a'].addString(s.substring(1), end);
		}
		
		public String replaceString(String s){
			return replaceString(0, 0, s, this);
		}
		
		public String replaceString(int x, int d, String s, Trie root){
			if(x+d>=s.length()) return s;
			char c = s.toLowerCase().charAt(x+d);
			if(end!=null){
				return (s.substring(0,x) + end + root.replaceString(0,0,s.substring(x+d),root));
			}
			if(c-'a'<0||c-'a'>=26){return root.replaceString(x+1, 0, s, root);}
			else if(alphabet[c-'a']!=null){
				return alphabet[c-'a'].replaceString(x, d+1, s, root);
			}
			else{
				return root.replaceString(x+1,0,s,root);
			}
		}
	}
	
	public void start() {
		Trie t = new Trie();
		t.addString("Jano","Dezo");
		String us = "Jano je strasny janoidlo.";
		//t.replaceString(us);
		System.out.println(us);
		System.out.println(t.replaceString(us));
	}

	@Override
	public void help() {
	}

}
