package stream;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class People
{
	private String name;
	private int age;
	private int gender;
	
	public static List<People> getPeopleList(){
		return Arrays.asList(
				new People("홍길동", 17, 1),
				new People("이길동", 18, 2),
				new People("최길동", 15, 2),
				new People("박길동", 17, 1));
	}
	
}
