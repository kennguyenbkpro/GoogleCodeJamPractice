package ken.codejam;

import java.io.BufferedWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.sql.rowset.FilteredRowSet;

import ken.codejam.utils.FirstLineNumOfTCProblem;

/**
 * Problem

As the leader of the Evil League of Evil, Bad Horse has a lot of problems to deal with. Most recently, there have been far too many arguments and far too much backstabbing in the League, so much so that Bad Horse has decided to split the league into two departments in order to separate troublesome members. Being the Thoroughbred of Sin, Bad Horse isn't about to spend his valuable time figuring out how to split the League members by himself. That what he's got you -- his loyal henchman -- for.

Input

The first line of the input gives the number of test cases, T. T test cases follow. Each test case starts with a positive integer M on a line by itself -- the number of troublesome pairs of League members. The next M lines each contain a pair of names, separated by a single space.

Output

For each test case, output one line containing "Case #x: y", where x is the case number (starting from 1) and y is either "Yes" or "No", depending on whether the League members mentioned in the input can be split into two groups with neither of the groups containing a troublesome pair.

Limits

1 ≤ T ≤ 100.
Each member name will consist of only letters and the underscore character.
Names are case-sensitive.
No pair will appear more than once in the same test case.
Each pair will contain two distinct League members.

Small dataset

1 ≤ M ≤ 10.

Large dataset

1 ≤ M ≤ 100.

Sample


Input 
 	
Output 
 
2
1
Dead_Bowie Fake_Thomas_Jefferson
3
Dead_Bowie Fake_Thomas_Jefferson
Fake_Thomas_Jefferson Fury_Leika
Fury_Leika Dead_Bowie
Case #1: Yes
Case #2: No


 * @author khanhnq2
 *
 */
public class BadHorse extends FirstLineNumOfTCProblem{

	public BadHorse() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new BadHorse().start();
	}

	int state = 0;
	int M = 0;
	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		if (state == 0){
			M = Integer.valueOf(line);
			state = 1;
		} else if (state == 1){
			((TestCase) testcase).addPair(line);
			M--;
			if (M == 0){
				state = 0;
				return true;
			}
		}
		return false;
	}
	
	public static class Member {
		public String name;
		public Set<Member> troubleMember = new HashSet<>();
		
		public Member(String name){
			this.name = name;
		}
		
		public int team = 0;
	}
	
	public static class TestCase extends BaseTestCase {
		HashMap<String, Member> memberMap = new HashMap<>();
		
		Member findMemeber(String name){
			if (memberMap.containsKey(name)){
				return memberMap.get(name);
			} else {
				Member member = new Member(name);
				memberMap.put(name, member);
				return member;
			}
		}
		
		public void addPair(String line){
			String[] val = line.split(" ");
			Member mem1 = findMemeber(val[0]);
			Member mem2 = findMemeber(val[1]);
			mem1.troubleMember.add(mem2);
			mem2.troubleMember.add(mem1);
		}

		@Override
		public void process(int order, BufferedWriter output) {
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
		
		//Return true if circle exist
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
