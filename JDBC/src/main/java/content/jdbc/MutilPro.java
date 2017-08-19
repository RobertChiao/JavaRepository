package ne.packet;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

import test.jdbc.PoolUtils;

public class MutilPro {
	/**
	 * ִ��һ��DDL
	 * ��֧������
	 * @param args
	 */
	public static void main(String[] args) {
		String sql1="create table log_01 "
				+ "(id number(8), "
				+ "msg varchar2(100))";
		String sql2="create table log_02 "
				+ "(id number(8), "
				+ "msg varchar2(100))";
		String sql3="create table log_03 "
				+ "(id number(8), "
				+ "msg varchar2(100))";
		//ִ��һ��sql
		Connection conn=null;
		try {
			conn=PoolUtils.getConnection();
			Statement st=conn.createStatement();
			//sql1��ӵ�Statement�Ļ�������
			st.addBatch(sql1);
			st.addBatch(sql2);
			st.addBatch(sql3);
			//����ִ��
			int[] arr=st.executeBatch();
			System.out.println(Arrays.toString(arr));
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			PoolUtils.close(conn);
		}
	}

}
