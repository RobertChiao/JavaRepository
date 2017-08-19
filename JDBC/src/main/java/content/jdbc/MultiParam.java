package ne.packet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import test.jdbc.PoolUtils;

/**
 * ������������
 * @author bokun_sx1
 *
 */
public class MultiParam {

	public static void main(String[] args) {
		String sql="insert into robin_user "
				+ "(id,name,pwd)"
				+ "values  (?,?,?)";
		Connection conn=null;
		try {
			conn=PoolUtils.getConnection();
			PreparedStatement ps=conn.prepareStatement(sql);
			
			for(int i=0;i<100;i++){
				//�滻����
				ps.setInt(1, i);
				ps.setString(2, "name"+i);
				ps.setString(3, "123");
				//��������ӵ�������
				ps.addBatch();
				//�����ڴ����
				if((i+1%8)==1){
					
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			//����ִ��
			int[] arr=ps.executeBatch();
			System.out.println(Arrays.toString(arr));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			PoolUtils.close(conn);
		}

	}

}
