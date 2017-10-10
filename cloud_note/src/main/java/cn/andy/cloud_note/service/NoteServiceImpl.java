package cn.andy.cloud_note.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.andy.cloud_note.dao.NoteDao;
import cn.andy.cloud_note.entity.Note;
import cn.andy.cloud_note.util.NoteResult;

@Service("noteService")

public class NoteServiceImpl implements NoteService {
	
	@Resource(name="noteDao")
	private NoteDao noteDao;
	
	public NoteResult<List<Map>> loadBookNotes(String bookId) {
		NoteResult<List<Map>> result =new NoteResult<List<Map>>();
		
		List<Map> notes=noteDao.findByBookId(bookId);
		if(notes==null){
			result.setMsg("����û�мǱʼ�Ŷ��");
			result.setStatus(1);
			return result;
		}
		result.setData(notes);
		result.setMsg("�����ʼǳɹ�");
		result.setStatus(0);
		return result;
	}

	public NoteResult<Map> loadNote(String noteId) {
		NoteResult<Map> result =new NoteResult<Map>();
		Map noteMap=noteDao.findByNoteId(noteId);
		if(noteMap==null){
			result.setMsg("�����ʼ�����Ϊ��");
			result.setStatus(1);
			return result;
		}
		result.setData(noteMap);
		result.setMsg("�ʼ����ݼ��سɹ�");
		result.setStatus(0);
		return result;
	}

	public NoteResult updateNote(Note note) {
		NoteResult result=new NoteResult();	
		
		int rows=noteDao.updateNote(note);
		
		if(rows==1){
			
			result.setMsg("�������ݳɹ�");
			result.setStatus(0);
			
			return result;
		}
		result.setMsg("��������ʧ��");
		result.setStatus(1);
		return result;
	}

	public NoteResult addNote(String bookId, String noteTitle, String userId) {
		NoteResult result=new NoteResult();
		UUID noteId=UUID.randomUUID();
		System.out.println(noteId.toString());
		Note note=new Note();
		note.setCn_note_title(noteTitle);
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_id(noteId.toString());
		note.setCn_note_status_id("1");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		note.setCn_note_body("");
		int n=noteDao.save(note);
		if(n==1){
			result.setMsg("�����ʼǳɹ���");
			result.setStatus(0);
			result.setData(noteId.toString());
			return result;
		}
		result.setMsg("�����ʼ�ʧ�ܣ�");
		result.setStatus(1);
		return result;
	}

	public NoteResult deleteNote(String noteId) {
		NoteResult result=new NoteResult();
		
		int n=noteDao.delete(noteId);
		if(n==1){
			result.setStatus(0);
			result.setMsg("ɾ���ɹ���");
			return result;
		}
		result.setStatus(1);
		result.setMsg("ɾ��ʧ�ܣ�");
		return result;
	}

	public NoteResult<List<Map>> loadReceiveNote(String userId) {
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		
		List<Map> notes=noteDao.findReceiveNote(userId);
		if(!notes.isEmpty()){
			result.setMsg("����վ���سɹ���");
			result.setData(notes);
			result.setStatus(0);
			return result;
		}
		result.setMsg("����վ����ʧ�ܣ�");
		result.setStatus(1);
		return result;
	}

}
