package cs.programs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import cs.CSProgram;

public class Merging extends CSProgram {

	private class Entry {
		public int id;
		public int children;
		
		/*public Entry(int id, int children){
			this.id=id;
			this.children=children;
		}*/
		
		public Entry(String ent){
			if(ent==null||ent.trim().equals("")) {this.id=-1; return;}
			this.id=Integer.parseInt(ent.split(";")[0]);
			this.children=Integer.parseInt(ent.split(";")[1]);
		}
		
		@Override
		public String toString() {
			return id+";"+children;
		}
		
		public boolean hasZeroChildren(){
			return children==0;
		}
		
		public boolean hasNegativeChildren(){
			return children<0;
		}
	}
	
	public Merging(int id) {
		super("Merging", id);
	}

	@Override
	public void start() {
		ArrayList<Entry> out = new ArrayList<Entry>();
		try {
			BufferedReader master = new BufferedReader(new FileReader("masterChildren.txt"));
			BufferedReader trans = new BufferedReader(new FileReader("transactionChildren.txt"));
			Entry tran = new Entry(trans.readLine());
			Entry mast = new Entry(master.readLine());
			while(true){
				if(mast.id==-1&&tran.id==-1){break;}
				if(mast.id==-1){
					out.add(tran);
					while(tran.id!=-1){
						tran = new Entry(trans.readLine());
						out.add(tran);
					}
					break;
				}
				if(tran.id==-1){
					out.add(mast);
					while(mast.id!=-1){
						mast = new Entry(master.readLine());
						out.add(mast);
					}
					break;
				}
				if(tran.id>mast.id){
					out.add(mast);
					mast = new Entry(master.readLine());
				}
				else if(tran.id==mast.id){
					mast.children+=tran.children;
					out.add(mast);
					mast = new Entry(master.readLine());
					tran = new Entry(trans.readLine());
				}
				else if(tran.id<mast.id){
					out.add(tran);
					tran = new Entry(trans.readLine());
				}
			}
			master.close();
			trans.close();
		} catch (IOException e) {e.printStackTrace();}
		try {
			BufferedWriter master = new BufferedWriter(new FileWriter("newMasterChildren.txt"));
			for(Entry e : out){
				if(e.hasZeroChildren()){continue;}
				if(e.hasNegativeChildren()){System.out.println("Errrrrorrrrr! " + e.id + " " + e.children); break;}
				master.write(e.toString()+"\n");
			}
			master.close();
		} catch (IOException e) {e.printStackTrace();}
	}

	@Override
	public void help() {
		// TODO Auto-generated method stub

	}

}
