package com.ssafy.web.service;

import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.ssafy.web.dto.HouseDealDTO;

@Service
public class HouseDealService {
	public ArrayList<HouseDealDTO> getHouseDealList(String code, String date) throws Exception {
		try {
			ArrayList<HouseDealDTO> list = new ArrayList<>();

			StringBuilder urlBuilder = new StringBuilder(
					"http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*
																																 * URL
																																 */
			urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
					+ "=s3vJbGZnD22NN0teVvaHfwzRsFY6lxy1gvtpQQOU7rFhEW%2BwnSFqAD0yrZn8vjQl41H5P2ZWXiqUikL15T9yKA%3D%3D"); /*
																															 * Service
																															 * Key
																															 */
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("4000", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append(
					"&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(code, "UTF-8")); /* 지역코드 */
			urlBuilder.append(
					"&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8")); /* 계약월 */

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());

			SAXParserFactory f = SAXParserFactory.newInstance();
			SAXParser parser = f.newSAXParser();
			parser.parse(conn.getInputStream(), new DefaultHandler() {
				HouseDealDTO h;
				String qName;

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					if (qName.equals("item")) {
						h = new HouseDealDTO();
					}
					this.qName = qName;
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					if (qName.equals("거래금액")) {
						String money = new String(ch, start, length);
						h.setDealAmount(money.trim());
					} else if (qName.equals("아파트")) {
						h.setAptName(new String(ch, start, length));
					} else if (qName.equals("법정동")) {
						String dong = new String(ch, start, length);
						h.setDong(dong.trim());
					} else if (qName.equals("건축년도")) {
						h.setBuiltYear(Integer.parseInt(new String(ch, start, length)));
					} else if (qName.equals("전용면적")) {
						h.setArea(new String(ch, start, length));
					} else if (qName.equals("지번")) {
						h.setAreaNumber(new String(ch, start, length));
					} else if (qName.equals("년")) {
						h.setDealYear(Integer.parseInt(new String(ch, start, length)));
					} else if (qName.equals("월")) {
						h.setDealMonth(Integer.parseInt(new String(ch, start, length)));
					} else if (qName.equals("일")) {
						h.setDealDay(Integer.parseInt(new String(ch, start, length)));
					} else if (qName.equals("층")) {
						h.setFloor(Integer.parseInt(new String(ch, start, length)));
					}
				}

				@Override
				public void endElement(String uri, String localName, String qName) throws SAXException {
					if (qName.equals("item")) {
						list.add(h);
						h = null;
					}
				}
			});

			System.out.println(list.size());
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("주택 거래 조회 실패");
		}
	}
}
