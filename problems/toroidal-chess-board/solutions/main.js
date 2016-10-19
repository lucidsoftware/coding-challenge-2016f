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

function dist1(x1,x2) {
    return Math.min(Math.abs(x1-x2),8-Math.abs(x1-x2));
}

function distance(p1,p2) {
    return Math.max(dist1(p1[0],p2[0]),dist1(p1[1],p2[1]));
}

readStdin(function(data){
    var tests = parseInt(data[0], 10);
    for(var i=1; i<=tests*2; i+=2) {
        var p1 = data[i].split(' ').map(function(n){return parseInt(n,10)});
        var p2 = data[i+1].split(' ').map(function(n){return parseInt(n,10)});
        console.log(distance(p1,p2));
    }
});