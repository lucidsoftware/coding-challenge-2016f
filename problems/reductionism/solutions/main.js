var readStdin = function(callback){
    var data = '';
    process.stdin.resume();
    process.stdin.setEncoding('utf8');
    process.stdin.on('data', function(chunk) {
        data += chunk.toString();
    });
    return process.stdin.on('end', function() {
        callback(data);
    });
};


readStdin(function(data){
	console.log(data.split('').join('\n'));
});