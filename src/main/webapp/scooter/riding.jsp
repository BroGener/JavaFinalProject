<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Riding</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 24px; text-align: center; }
        .card { border: 1px solid #ddd; padding: 32px; margin: 24px auto; max-width: 400px; }
        .big { font-size: 48px; font-weight: bold; color: #4CAF50; }
        a.btn-red { display: inline-block; padding: 12px 32px; background: #f44336; 
                    color: white; text-decoration: none; border-radius: 6px; 
                    font-size: 18px; margin-top: 16px; }
    </style>
    <script>
        // 显示骑行计时器
        var start = new Date().getTime();
        setInterval(function() {
            var elapsed = Math.floor((new Date().getTime() - start) / 1000);
            var mins = Math.floor(elapsed / 60);
            var secs = elapsed % 60;
            document.getElementById('timer').innerText = 
                mins + 'm ' + (secs < 10 ? '0' : '') + secs + 's';
            // 实时估算费用
            var fee = (elapsed  * 5.00 ).toFixed(2);
            document.getElementById('fee').innerText = '$' + fee;
        }, 1000);
    </script>
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<div class="card">
    <h1>🛴 Riding...</h1>
    <p>Scooter: <strong>${param.scooterId}</strong></p>
    <p>Time: <span id="timer" class="big">0m 00s</span></p>
    <p>Estimated fee: <span id="fee">$0.50</span></p>
    <br/>
    <a class="btn-red" href="${pageContext.request.contextPath}/scooters?action=return&scooterId=${param.scooterId}">
        Return Scooter
    </a>
</div>
</body>
</html>