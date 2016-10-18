var fs = require('fs');
for(var i=5; i<=50; i++) {
	var bits = Math.floor(Math.random()*22)+11;
	var a = Math.floor(Math.random()*Math.pow(2,bits));
	var b = Math.floor(Math.random()*Math.pow(2,bits));
	if(Math.random() < 0.5) {
		b = a.toString(2).split('').map(a => (a-1)*-1).join('');
		while(b.length < bits) b = '1' + b;
		b = parseInt(b,2);
	}
	var str = bits + '\n' + a + ' ' + b;
	// fs.writeFileSync('../tests/'+i+'.in', str)
}

