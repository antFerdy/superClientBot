package models;

public class Update {
	private long update_id;
	private Message message;
	
	public long getUpdate_id() {
		return update_id;
	}
	public void setUpdate_id(long update_id) {
		this.update_id = update_id;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
}
