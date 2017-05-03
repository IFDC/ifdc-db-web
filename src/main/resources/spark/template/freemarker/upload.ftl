<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="container">

    <h1>UPLOAD PAGE</h1>
    <form id="UploadForm" method="post" enctype="multipart/form-data">
        <label>Select a file : </label>
        <input type="file" name="file" required>
        <input type="submit" value="Upload It" />
    </form>
</div>

</body>
</html>
