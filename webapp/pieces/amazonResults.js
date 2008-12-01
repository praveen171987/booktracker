var AmazonResult = new Class({	
	Implements: [Options, Events],
	options: {
	},
	initialize: function(el, options){
		window.addEvent('resize', function() {
			$('amazon').setStyle('height',$($('amazon').getParent().getParent()).getStyle('height').toInt()-$('pages').clientHeight-2);
		});
		this.amazCurrPage = 1;
		var pages = new Element('div').set('id','pages');
		var amazon = new Element('div').set('id','amazon');
		$(el).appendChild(pages);
		$(el).appendChild(amazon);
	},
	loadQuery: function(json){
		var i=0;
		if(json.totPages && json.totPages > 1){
			var back = this.amazCurrPage==1?'&nbsp;':'<a href="#" onClick="resultsPane.wrapGetQuery(null, -1)"><</a>';
			var fore = this.amazCurrPage==json.totPages?'&nbsp;':'<a href="#" onClick="resultsPane.wrapGetQuery(null, 1)">></a>';
			$('pages').set('html', back+' page '+this.amazCurrPage+' of '+json.totPages+' '+fore);
		}
		
		$('amazon').set('html','');
		if(json.data) {
			while(json.data[i]){
				var div = new Element('div', {id: 'result'+i, class: 'entry'});
				div.data = json.data[i];
				var dataDiv = new Element('div', {class: 'json.data'});
				var leftDiv = new Element('div', {class: 'left'});
				var img = new Element('img', {src: json.data[i].small_url});
				var addLib = new Element('a',{'href':'#'}).set('html','add Lib')
				addLib.addEvent('click', function(){
					submitRequest('bk',this.data.isbn, {amazonData:this.data})
				}.bind(div));
				var addPlay = new Element('a',{'href':'#'}).set('html','add Ply');
				
				var libLink = new Element('a',{'href':'#'}).set('html','library');
				var amazLink = new Element('a',{'href':json.data[i].detailUrl}).set('html','amazon');
				var title = new Element('div').set('html',json.data[i].title).set('class','title');
				var openParen = json.data[i].title.indexOf('(');
				div.data.series = "";
				var seriesDiv;
				if(openParen>=0){
					
					//Might be part of a series
					var comma = json.data[i].title.indexOf(',',openParen);
					var closeParen = json.data[i].title.indexOf(')',openParen);
					
					if(comma>=0){
						div.data.series = json.data[i].title.substring(openParen+1, comma);
					}
					else if(openParen>=0){
						div.data.series = json.data[i].title.substring(openParen+1,closeParen);
					}
					//else unmatches parens, ignore them
					
					if(div.data.series != null && div.data.series != "") {
						seriesDiv = new Element('div').set('html','This book might be part of a series, search for it?').addEvent('click', function(ev) {
							$('text').value = this.data.series;
							getQuery(this.data.series);
						}.bind(div)).addClass('series');
					}
					
				}
				var author = new Element('div').set('html','by: '+json.data[i].author);
				var rating = new Element('div').set('html',json.data[i].amaz_rating);
				var tags = new Element('div').set('class','tags');
				if(json.data[i].tags)
					tags.set('html',json.data[i].tags.replace(/,/g,", "));
				dataDiv.grab(title).grab(author).grab(rating).grab(tags);
				leftDiv.grab(img).grab(addLib).grab(addPlay).grab(libLink).grab(amazLink);
				if(div.data.series != null && div.data.series != ""){
					dataDiv.grab(seriesDiv);
					div.addClass('tall');
				}
				div.grab(leftDiv).grab(dataDiv);
				
				$('amazon').grab(div);
				i++;
			}
		}
	},
	wrapGetQuery: function(a, b) {
		getQuery(null, b);
		this.amazCurrPage += b;
	}
});