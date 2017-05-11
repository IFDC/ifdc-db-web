<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="container">

    <h1>Import M&E Data Page</h1>
    <form id="UploadForm" method="post" enctype="multipart/form-data">
        <label>Select a file : </label>
        <input type="file" name="file" required><br>
        <input type="submit" value="Load" />
    </form>
</div>

</body>
</html>
