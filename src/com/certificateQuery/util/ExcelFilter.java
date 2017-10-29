package com.certificateQuery.util;

import java.util.List;

import com.certificateQuery.model.WorkBean;

public class ExcelFilter {
	private static final char[] unlegal = { '>', '<', ',', '%', '\'', '\\', '?', '/', ';' };

	public String LengthFilter(List<WorkBean> worklist) {
		String errorResult = "";
		for (int i = 0, n = 1; i < worklist.size(); i++, n++) {
			WorkBean work = worklist.get(i);
			if ((work.getArea()).length() > 10)
				errorResult += "第" + n + "行数据的参赛地区过长,";
			if (work.getYear() > 9999)
				errorResult += "第" + n + "行数据的参赛时间过长,";
			if (work.getMonth() > 99)
				errorResult += "第" + n + "行数据的参赛月份过长,";
			if ((work.getLevel()).length() > 10)
				errorResult += "第" + n + "行数据的奖项过长,";
			if ((work.getSchool()).length() > 10)
				errorResult += "第" + n + "行数据的学校过长,";
			if ((work.getNumber()).length() > 16)
				errorResult += "第" + n + "行数据的证书编号过长,";
			if ((work.getWorkid()).length() > 20)
				errorResult += "第" + n + "行数据的参赛编号过长,";
			if (work.getWorkname().length() > 50)
				errorResult += "第" + n + "行数据的作品名称过长,";
			if (work.getStudent1().length() > 20)
				errorResult += "第" + n + "行数据的学生1过长,";
			if (work.getStudent1Id().length() > 18)
				errorResult += "第" + n + "行数据的学生1Id过长,";
			if (work.getStudent2().length() > 20)
				errorResult += "第" + n + "行数据的学生2过长,";
			if (work.getStudent2Id().length() > 18)
				errorResult += "第" + n + "行数据的学生2Id过长,";
			if (work.getStudent3().length() > 20)
				errorResult += "第" + n + "行数据的学生3过长,";
			if (work.getStudent3Id().length() > 18)
				errorResult += "第" + n + "行数据的学生3Id过长,";

			if (work.getTeacher1().length() > 20)
				errorResult += "第" + n + "行数据的指导老师1过长,";
			if (work.getTeacher1Id().length() > 18)
				errorResult += "第" + n + "行数据的指导老师1Id过长,";

			if (work.getTeacher2().length() > 20)
				errorResult += "第" + n + "行数据的指导老师2过长,";
			if (work.getTeacher2Id().length() > 18)
				errorResult += "第" + n + "行数据的指导老师2Id过长,";
		}
		if (errorResult.equals("")) {
			return null;
		} else {
			return errorResult;
		}
	}

	public void CharFilter(List<WorkBean> list) {
		for (int i = 0; i < list.size(); i++) {
			WorkBean work = list.get(i);
			work.setArea(judgeString(work.getArea()));
			work.setLevel(judgeString(work.getLevel()));
			work.setNumber(judgeString(work.getNumber()));
			work.setSchool(judgeString(work.getSchool()));
			work.setWorkid(judgeString(work.getWorkid()));
			work.setWorkname(judgeString(work.getWorkname()));
			work.setStudent1(judgeString(work.getStudent1()));
			work.setStudent1Id(judgeString(work.getStudent1Id()));
			work.setStudent2(judgeString(work.getStudent2()));
			work.setStudent2Id(judgeString(work.getStudent2Id()));
			work.setStudent3(judgeString(work.getStudent3()));
			work.setStudent3Id(judgeString(work.getStudent3Id()));
			work.setTeacher1(judgeString(work.getTeacher1()));
			work.setTeacher1Id(judgeString(work.getTeacher1Id()));
			work.setTeacher2(judgeString(work.getTeacher2()));
			work.setTeacher2Id(judgeString(work.getTeacher2Id()));
		}
	}

	public String judgeString(String s) {
		for (char c : unlegal) {
			s = s.replace(c, ' ');
		}
		return s;
	}
}
