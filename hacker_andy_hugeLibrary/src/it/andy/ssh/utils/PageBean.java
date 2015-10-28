package it.andy.ssh.utils;

import java.util.List;

/**
 * 	分页工具，对于分页，其实不难，理解一下逻辑，随便拿去用！
 * @author User
 *
 */
public class PageBean<T> {
		
	private Integer curPage;	      //当前页	
	private Integer totalPage;        //总页数
	
	private Integer prePage;		  //上一页
	private Integer nextPage;		  //下一页
	
	private Integer pageCount = 2;	  //当前页显示信息数目
	private Integer totalCount;       //信息总数目
	
	private List<T> pageData;     //需要分页的信息
	
	/*
	 * 	这里需要注意的是，在java的机制里面，if语句的性能是很差的，所以优化代码时，应该考虑代换if语句
	 */
	
	//总页数
	public Integer getTotalPage() {
		/*if(this.totalCount%2==0){
			this.totalPage = this.totalCount / this.pageCount;
		}else{
			this.totalPage = (this.totalCount / this.pageCount) + 1;
		}
		return totalPage;*/
		
		//三目
		return this.getTotalCount()%2==0? 
				this.getTotalCount() / this.getPageCount(): 
				  this.getTotalCount() / this.getPageCount() +1;
		
	}
	
	//上一页
	public Integer getPrePage() {
		/*if(this.curPage>1){
			this.prePage  = this.curPage - 1;
		}else{
			this.prePage  = this.curPage;
		}*/
		return this.getCurPage()==this.getPrePage()? 
				1:this.getCurPage()-1;
		  
	}

	//下一页
	public Integer getNextPage() {
		
		return this.getCurPage()==this.getTotalPage()? 
				this.getTotalPage():
			     this.getCurPage()+1 ;
	}

	
	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}


	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}


	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}
	
	
}
