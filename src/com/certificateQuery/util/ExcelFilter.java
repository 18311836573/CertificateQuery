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
				errorResult += "��" + n + "�����ݵĲ�����������,";
			if (work.getYear() > 9999)
				errorResult += "��" + n + "�����ݵĲ���ʱ�����,";
			if (work.getMonth() > 99)
				errorResult += "��" + n + "�����ݵĲ����·ݹ���,";
			if ((work.getLevel()).length() > 10)
				errorResult += "��" + n + "�����ݵĽ������,";
			if ((work.getSchool()).length() > 10)
				errorResult += "��" + n + "�����ݵ�ѧУ����,";
			if ((work.getNumber()).length() > 16)
				errorResult += "��" + n + "�����ݵ�֤���Ź���,";
			if ((work.getWorkid()).length() > 20)
				errorResult += "��" + n + "�����ݵĲ�����Ź���,";
			if (work.getWorkname().length() > 50)
				errorResult += "��" + n + "�����ݵ���Ʒ���ƹ���,";
			if (work.getStudent1().length() > 20)
				errorResult += "��" + n + "�����ݵ�ѧ��1����,";
			if (work.getStudent1Id().length() > 18)
				errorResult += "��" + n + "�����ݵ�ѧ��1Id����,";
			if (work.getStudent2().length() > 20)
				errorResult += "��" + n + "�����ݵ�ѧ��2����,";
			if (work.getStudent2Id().length() > 18)
				errorResult += "��" + n + "�����ݵ�ѧ��2Id����,";
			if (work.getStudent3().length() > 20)
				errorResult += "��" + n + "�����ݵ�ѧ��3����,";
			if (work.getStudent3Id().length() > 18)
				errorResult += "��" + n + "�����ݵ�ѧ��3Id����,";

			if (work.getTeacher1().length() > 20)
				errorResult += "��" + n + "�����ݵ�ָ����ʦ1����,";
			if (work.getTeacher1Id().length() > 18)
				errorResult += "��" + n + "�����ݵ�ָ����ʦ1Id����,";

			if (work.getTeacher2().length() > 20)
				errorResult += "��" + n + "�����ݵ�ָ����ʦ2����,";
			if (work.getTeacher2Id().length() > 18)
				errorResult += "��" + n + "�����ݵ�ָ����ʦ2Id����,";
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
