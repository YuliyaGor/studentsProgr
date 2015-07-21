<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<div class="login_form_container">
  <form action="/login" method="post">

    <table id="login_table">
      <tr>
        <td colspan="2" class="caption">Вход</td>
      </tr>
      <tr>
        <td>Имя:</td>
        <td><input type="text" name="username" class="text" value="" /></td>
      </tr>
      <tr>
        <td>Пароль:</td>
        <td><input type="password" name="password" class="text" /></td>
      </tr>
      <tr>
        <td>Выбрать роль:</td>
        <td><select name="role" id="opening_list">
          <option value="1">Администратор</option>
          <option value="2">Студент</option>
        </select></td>
      </tr>
      <tr height="60">
        <td>&nbsp</td>
        <td><input type="submit" value="войти" id="button"></td>
      </tr>
    </table>

    <%--<jsp:include page="modules/validationMessage.jsp" />--%>
  </form>
</div>