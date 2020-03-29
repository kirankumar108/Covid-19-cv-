package com.covid19tracker.Dao;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.covid19tracker.Model.LocationData;

@Service
public class CoronaVirusDataService {

	private static String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	private List<LocationData> locationData=new ArrayList<>();
	public List<LocationData> getLocationData() {
		return locationData;
	}

	@PostConstruct
	 @Scheduled(fixedRate = 360000)
	public void fetchVirusData() throws ClientProtocolException, IOException
	{
	    List<LocationData> newlocationData=new ArrayList<>();
		HttpGet request = new HttpGet(VIRUS_URL);

		try (CloseableHttpResponse response = httpClient.execute(request)) {

			// Get HttpResponse Status
			System.out.println(response.getStatusLine().toString());

			HttpEntity entity = response.getEntity();
			Header headers = entity.getContentType();
			System.out.println(headers);
			String result=null;
			if (entity != null) {
				// return it as a String
				 result = EntityUtils.toString(entity);
				System.out.println(result);
			}
			
			StringReader csvReader=new StringReader(result);
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvReader);
			for (CSVRecord record : records) {
				
				LocationData locationdata=new LocationData();
				locationdata.setState(record.get("Province/State"));
				locationdata.setCountry(record.get("Country/Region"));
				  int latesCases=Integer.parseInt(record.get(record.size()-1));
				  int previousCases=Integer.parseInt(record.get(record.size()-2));
				  locationdata.setLatestStats(latesCases);
				  locationdata.setDiffFromPreviousDay(latesCases-previousCases);
				  System.out.println(locationdata);
				  
				newlocationData.add(locationdata);
			  
			}
			this.locationData=newlocationData;
		}
		
	}
}



