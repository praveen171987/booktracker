var BookInfo = new Class({
	Implements: [Options, Events],
	options: {
	},
	initialize: function(el, options){
		this.parent = $(el);
		this.parent.addClass('bookInfo');
	},
	infoString: "<img src='{large_url}'></img>"+
		"<div class='title'>{title}</div>"+
		"<div class='wrap'>"+	
			"<div><b>Author:</b> {author}</div>"+
			"<div><b>Amazon's Rating:</b> {amaz_rating}</div>"+
			"<div><b>Publication Date:</b> {pub_date}</div>"+
			"<div><b>Pages:</b> {pages}</div>"+
		"</div>",
	showInfo: function(book) {
		this.parent.set('html',this.infoString.substitute(book));
	}
});