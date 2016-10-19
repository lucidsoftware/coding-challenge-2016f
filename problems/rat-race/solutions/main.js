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
    var testCases = parseInt(data.shift());
    for(var i=0; i<testCases; i++) {
        var dimensions = data.shift().split(/\s+/).map(function(n) {return parseInt(n,10)});
        var board = data.splice(0,dimensions[1]);
        if(board.some(function(row) {
            return row.length != dimensions[0]
        })) {
            console.error('Invalid test case!');
            console.error(dimensions.join(' '));
            console.error(board.join('\n'));
        }
        solve(board);
    }
});


function solve(board) {
    var start = {x:0,y:0};
o:  for(var i=0; i<board[0].length; i++) {
        for(var j=0; j<board.length; j++) {
            if(board[j][i] == '.') {
                start = {x:i,y:j};
                break o;
            }
        }
    }
    var farthest = findFurthest(board, start);
    var result = findFurthest(board, farthest.pos);
    console.log('Maximum path length is ' + result.dist);
}

function findFurthest(board, pos) {
    var result = {
        pos:pos,
        dist:0
    }
    var seen = {};
    var stack = [{p:pos,d:0}];
    while(stack.length > 0) {
        var value = stack.pop();
        var pos = value.p;
        var dist = value.d;
        if(seen[pos.x+','+pos.y] || board[pos.y][pos.x] != '.') {
            continue;
        }
        if(dist > result.dist) {
            result.dist = dist;
            result.pos = pos;
        }
        seen[pos.x+','+pos.y] = true;
        if(pos.x > 0) stack.push({p:{x:pos.x-1,y:pos.y}, d:dist + 1});
        if(pos.y > 0) stack.push({p:{x:pos.x,y:pos.y-1}, d:dist + 1});
        if(pos.x < board[0].length-1) stack.push({p:{x:pos.x+1,y:pos.y}, d:dist + 1});
        if(pos.y < board.length-1) stack.push({p:{x:pos.x,y:pos.y+1}, d:dist + 1});
        
    }
    var count = 0;
    for(var s in seen) {
        count++;
    }
    if(count != board.join('').replace(/#/g,'').length) {
        console.log('not traversable', count, board.join('').replace(/#/g,'').length);
        console.log(board.join('\n'));
    }
    return result;
}