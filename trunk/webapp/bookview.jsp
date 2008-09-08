<html>
	<head>
		<!-- NiftyCube libraries -->
		<script type="text/javascript" src="scripts/NiftyCube/niftycube.js"></script>
		<link rel="stylesheet" href="scripts/NiftyCube/niftyCorners.css">
		
		<style type="text/css">
			div.round{
				width: 60em;
				padding: 20px;
				margin-top:8px;
			    background:#E6E6E6;
				color:#000
			}
			.cover{
				//position: absolute;
			}
			.info{
				float:left;
				width:100%;
				margin-top:-75px;
				height: 75px;
				//position: absolute;
			}
			.links{
				float:right;
				//position: absolute;
			}
			.rating{
				line-height:4;
				float:left;
			}
			.bio{
				padding-left: 76px;
				float:left;
			}
			.right{
				width:360px;
				float:right;
			}
			.title{
				font-family:sans-serif;
				font-size:30px;
				font-weight:bold;
			}
			.tags{
				font-size: 80%;
				line-height: 2;
			}
		</style>
		<script type="text/javascript">
			window.onload=function(){
				Nifty("div.round","big");
			}
		</script>
	</head>
	<body>
		<div class="round">
			<img class="cover" src="http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL75_.jpg"></img>
			<div class="info">
				<div class="bio">
					<div class="title">This is a title</div>
					<div class="author">by This Guy</div>
					<div class="tags">apple, banana, cranberry, author, title</div>
				</div>
				<div class="right">
					<div class="rating">* * * * *</div>
					<div class="links">
						<div class="library">Reserve from Library</div>
						<div class="amazon">Buy at Amazon</div>
					</div>
				</div>
			</div>
		</div>		
		<div class="round">
			<img class="cover" src="http://ecx.images-amazon.com/images/I/51yYy6tjdlL._SL75_.jpg"></img>
			<div class="info">
				<div class="bio">
					<div class="title">This is a title</div>
					<div class="author">by This Guy</div>
					<div class="tags">apple, banana, cranberry, author, title</div>
				</div>
				<div class="right">
					<div class="rating">* * * * *</div>
					<div class="links">
						<div class="library">Reserve from Library</div>
						<div class="amazon">Buy at Amazon</div>
					</div>
				</div>
			</div>
		</div>
		<div class="round">This is another rounded div</div>
	</body>
</html>