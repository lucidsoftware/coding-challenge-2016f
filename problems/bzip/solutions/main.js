var readStdin = function(callback){
    var data = '';
    process.stdin.resume();
    process.stdin.setEncoding('utf8');
    process.stdin.on('data', function(chunk) {
        data += chunk.toString();
    });
    return process.stdin.on('end', function() {
        callback(data.split('\n'));
    });
};


readStdin(function(data){
    var bits = parseInt(data[0], 10);
    var a = parseInt(data[1].split(' ')[0], 10);
    var b = parseInt(data[1].split(' ')[1], 10);
    console.log(((a^b) == (Math.pow(2,bits)-1)) ? 'YES' : 'NO');
});