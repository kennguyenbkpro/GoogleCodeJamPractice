package ken.codejam;

import java.io.BufferedWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

/**
 * Problem

link: https://code.google.com/codejam/contest/6234486/dashboard#s=p0


Input 

2
1
Dead_Bowie Fake_Thomas_Jefferson
3
Dead_Bowie Fake_Thomas_Jefferson
Fake_Thomas_Jefferson Fury_Leika
Fury_Leika Dead_Bowie

Output 

Case #1: Yes
Case #2: No


 * @author khanhnq2
 *
 */
public class BadHorse extends AutoParseInputProblem{

	public BadHorse() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new BadHorse().start();
	}
	
	public static class Member {
		public String name;
		public Set<Member> troubleMember = new HashSet<>();
		
		public Member(String name){
			this.name = name;
		}
		
		public int team = 0;
	}
	
	public static class TestCase extends AutoParseTestCase {
		HashMap<String, Member> memberMap = new HashMap<>();
		
		public Integer N;
		public String[][] couples;
		
		public TestCase(){
			super("N\n"
					+ "&(N)couples");
		}
		
		Member findMemeber(String name){
			if (memberMap.containsKey(name)){
				return memberMap.get(name);
			} else {
				Member member = new Member(name);
				memberMap.put(name, member);
				return member;
			}
		}

		public void addToMap(){
			for (String[] couple : couples){
				Member mem1 = findMemeber(couple[0]);
				Member mem2 = findMemeber(couple[1]);
				mem1.troubleMember.add(mem2);
				mem2.troubleMember.add(mem1);
			}
		}

		@Override
		public void process(int order, BufferedWriter output) {
			addToMap();
			
			Collection<Member> memList = memberMap.values();
			for (Member mem : memList){
				if (mem.team == 0){
					mem.team = 1;
					if (DFS(mem)){
						print(order, output, "No");
						return;
					}
				}
			}
			print(order, output, "Yes");
		}
		
		//Return true if conflict exist
		boolean DFS(Member member){
			for (Member troubleMem : member.troubleMember){
				if (troubleMem.team == 0){
					troubleMem.team = member.team == 1 ? 2 : 1;
					if (DFS(troubleMem)) return true;
				} else if (troubleMem.team == member.team){
					return true;
				}
			}
			return false;
		}

		@Override
		public void clear() {
			memberMap = new HashMap<>();
		}
		
	}

}
