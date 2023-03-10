package stream;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TestStream
{
	public static void main(String[] args)
	{
		// 일반적인 경우 
		System.out.println("여성 목록 : ");
		for (People people : People.getPeopleList())
		{
			if(people.getGender() == 2) 
			{
				System.out.println(people);
			}
		}
		
		// Stream 이용 - 필터 
		List<People> womanList = People.getPeopleList().stream()
				.filter(people -> people.getGender() == 1)
				.collect(Collectors.toList());
		
		System.out.println("남성 목록 : ");
		womanList.forEach(System.out::println);
		
		List<People> sortedList = People.getPeopleList().stream()
				.sorted(Comparator.comparing(People::getAge))
				.collect(Collectors.toList());
		
		System.out.println("나이순 목록 : ");
		sortedList.forEach(System.out::println);
		
		// 모든 항목이 조건을 만족하는지 allMatch
		boolean allMatch = People.getPeopleList().stream()
				.allMatch(people -> people.getAge() > 12);
		System.out.println("나이가 모두 12 보다 많은가? " + allMatch);
		
		// 모든 항목이 조건을 만족하는지 allMatch
		boolean anyMatch = People.getPeopleList().stream()
				.anyMatch(people -> people.getAge() > 19);
		System.out.println("한 명이라도 나이가 19 보다 많은가? " + anyMatch);
		
		Optional<People> maxPeople = People.getPeopleList().stream()
				.max(Comparator.comparing(People::getAge));
		if(maxPeople.isPresent()) {
			System.out.println("나이가 제일 많은 사람은? ");
			System.out.println(maxPeople.get());
		}
		
		Map<Integer, List<People>> groupByGender = People.getPeopleList().stream()
				.collect(Collectors.groupingBy(People::getGender));
		System.out.println("남성/여성 목록 : ");
		groupByGender.forEach((gender, peopleList) -> {
			System.out.println(gender);
			peopleList.forEach(System.out::println);
		});
		
		Optional<String> oldestWoman = People.getPeopleList().stream()
				.filter(people -> people.getGender() == 2)
				.max(Comparator.comparing(People::getAge))
				.map(People::getName);
		
		System.out.println("여성 중에 나이가 제일 많은 사람 : ");
		oldestWoman.ifPresent(System.out::println);
	}
}
