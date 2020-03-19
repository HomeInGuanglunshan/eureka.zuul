package eureka.zuul.security.service;

import java.util.List;

import eureka.zuul.security.entity.Blog;

public interface IBlogService {
	/**
	 * 获取所有Blog
	 *
	 * @return
	 */
	List<Blog> getBlogs();

	/**
	 * 根据ID获取Blog
	 *
	 * @param id
	 */
	void deleteBlog(long id);
}
