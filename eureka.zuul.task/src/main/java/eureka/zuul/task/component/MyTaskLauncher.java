package eureka.zuul.task.component;

import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;
import org.springframework.cloud.deployer.spi.core.RuntimeEnvironmentInfo;
import org.springframework.cloud.deployer.spi.task.TaskLauncher;
import org.springframework.cloud.deployer.spi.task.TaskStatus;

public class MyTaskLauncher implements TaskLauncher {

	@Override
	public void cancel(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public RuntimeEnvironmentInfo environmentInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String launch(AppDeploymentRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskStatus status(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
