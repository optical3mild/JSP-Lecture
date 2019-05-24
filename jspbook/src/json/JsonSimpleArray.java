package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonSimpleArray {
	public static void main(String[] args) {
		System.out.println("----------------------------");
		System.out.println("JSON String 생성");
		JSONObject jObj = new JSONObject();
		
		JSONArray jArray = new JSONArray();
		jArray.add("국어");
		jArray.add("math");
		jArray.add("english");
		jObj.put("subject", jArray);
		
		System.out.println(jObj.toString());
		
		System.out.println("----------------------------");
		System.out.println("JSON String 파싱");
		try {
			JSONArray rSub = (JSONArray) jObj.get("subject");
			System.out.println("Size= " + rSub.size());
			for(int i=0; i<rSub.size(); i++) {
				System.out.println("Subject["+i+"]: "+(String)rSub.get(i));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("----------------------------");
	}
	
}
