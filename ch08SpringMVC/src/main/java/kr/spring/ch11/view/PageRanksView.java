package kr.spring.ch11.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.spring.ch11.vo.PageRank;

public class PageRanksView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//시트 생성
		HSSFSheet sheet = createFirstSheet((HSSFWorkbook)workbook);//필요한 건 HSSFWorkbook인데 부모 타입인 Workbook으로 넘어온다. 그래서 다운캐스팅
		
		//열이름 생성
		createColumnLabel(sheet);
		
		//시트에 데이터 표시하기
		List<PageRank> pageRanks = (List<PageRank>)model.get("pageRanks");//컨트롤러에서 제공한 모델 데이터를 Map이 갖고 있다.
		int rowNum = 1;//0번을 사용했기 때문에 행 번호 1로 지정
		for(PageRank rank : pageRanks) {
			createPageRankRow(sheet, rank, rowNum++);//1이 전달되고 다음에 루프를 돌 때 2가 전달됨. ++rowNum이라고 할거면 rowNum을 0으로 지정했어야 한다.
		}
		
		//HTTP 응답 메세지 헤더 설정
		String fileName = "pageRanks2024.xls";//원하는 파일명으로 지정
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}
	
	//시트 생성
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {//HSSFWorkbook : 시트를 생성하는 역할
		HSSFSheet sheet = workbook.createSheet();//시트 객체
									//sheet index,이름
		workbook.setSheetName(0, "페이지 순위");//시트 이름 지정 (시트순서,이름)
		//특정 컬럼에 넓이 지정
									//column Index,width
		sheet.setColumnWidth(1, 256*20);//시트 안에 컬럼이 있는데 컬럼의 넓이를 지정. 기본 넓이가 아니라 1번컬럼의 넓이를 지정함.
		
		return sheet;
	}
	//열 이름 생성
	private void createColumnLabel(HSSFSheet sheet) {//시트 생성한 것을 받아서 열 이름 생성
		HSSFRow firstRow = sheet.createRow(0);//HSSFRow : 행 정보를 갖고 있음
		HSSFCell cell = firstRow.createCell(0);//0번 방의 0번 셀
		cell.setCellValue("순위");
		
		cell = firstRow.createCell(1);
		cell.setCellValue("페이지");
	}
	//표시할 데이터 생성
	private void createPageRankRow(HSSFSheet sheet, PageRank rank,int rowNum) {//0번 행은 사용하지 않고 1번 행부터 루프를 돌면서 데이터를 기록하기 때문에 순서값이 필요하다.
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getRank());
		
		cell = row.createCell(1);
		cell.setCellValue(rank.getPage());
	}
}
