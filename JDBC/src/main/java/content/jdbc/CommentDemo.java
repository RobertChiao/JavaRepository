package ne.packet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import test.jdbc.PoolUtils;

public class CommentDemo {

	public static void main(String[] args) {
		
		Connection conn=null;
		try {
			conn=PoolUtils.getConnection();
			conn.setAutoCommit(false);
			String sql="insert into r_keywords(id,word) "
					+ "values (k_seq.nextval,?)";
			String[] cols={"id"};
			PreparedStatement ps=conn.prepareStatement(sql, cols);
			ps.setString(1, "����");
			int n=ps.executeUpdate();
			if(n!=1){
				throw new Exception ("�������ʧ��");
			}
			//��ȡ�Զ����ɵ�ID
			ResultSet rs=ps.getGeneratedKeys();
			int id=-1;
			while(rs.next()){
				id=rs.getInt(1);
			}
			ps.close();
			String sql2="insert into r_post "
					+ "(id,content,k_id) values "
					+ "(p_seq.nextval,?,?)";
			ps=conn.prepareStatement(sql2);
			ps.setString(1, "������������");
			ps.setInt(2, id);
			n=ps.executeUpdate();
			if(n!=1){
				throw new Exception("������");
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			PoolUtils.close(conn);
		}

	}

}
