function ObjDirectionsInfo(dirn, step, poly, eol, marker, stepnum, d, start, end){
	this.dirn = dirn;
	this.step = step;
	this.poly = poly;
	this.eol = eol;
	this.icon = new google.maps.MarkerImage(null);
	this.marker = marker;
	this.stepnum = stepnum;
	this.d = d;
	this.start = start;
	this.end = end;
}
