package json;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonBook {

	public static void main(String[] args) {
		System.out.println("----------------------------");
		List<BookDTO> bList = new ArrayList<BookDTO>();
		bList.add(new BookDTO("전쟁과 평화","톨스토이",20000,"소설","톨스토이 출판사"));
		bList.add(new BookDTO("홍길동전","허균",30000,"소설","허균 출판사"));
		bList.add(new BookDTO("레미제라블","빅토르 위고",10000,"소설","위고 출판사"));
		
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		JSONArray jArray = new JSONArray();
		for(BookDTO bDto: bList) {
			JSONObject book = new JSONObject();
			book.put("name",bDto.getName());
			book.put("writer",bDto.getWriter());
			book.put("price",bDto.getPrice());
			book.put("genre",bDto.getGenre());
			book.put("publisher",bDto.getPublisher());
			jArray.add(book);
			System.out.println(bDto.toString());
		}
		jObj.put("BookList", jArray);
		
		System.out.println(jObj.toString());
		
		
		System.out.println("----------------------------");
		System.out.println("JSON String 파싱");
		try {
			JSONArray returnBookList = (JSONArray) jObj.get("BookList");
			System.out.println("Size= " +returnBookList.size());
			for(int i=0; i<returnBookList.size(); i++) {
				JSONObject bookInfo = (JSONObject) returnBookList.get(i);
				BookDTO book = new BookDTO();
				book.setName((String) bookInfo.get("name"));
				book.setWriter((String) bookInfo.get("writer"));
				book.setPrice((Integer) bookInfo.get("price"));
				book.setGenre((String) bookInfo.get("genre"));
				book.setPublisher((String) bookInfo.get("publisher"));
				
				System.out.println(book.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("----------------------------");

	}

}
