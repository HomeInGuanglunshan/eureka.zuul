package eureka.zuul.boy.backup.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "U_USER")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GenericGenerator(name = "persistenceGenerator", strategy = "increment")
	Integer id;

	@Column(name = "NICKNAME")
	String nickname;

	@Column(name = "EMAIL")
	String email;

	@Column(name = "PSWD")
	String pswd;

	@Column(name = "CREATE_TIME")
	Date createTime;

	@Column(name = "LAST_LOGIN_TIME")
	Date lastLoginTime;

	@Column(name = "STATUS")
	Integer status;
}
