Phần 1 - Phân tích logic
CSRF (Cross-Site Request Forgery) là một cơ chế bảo vệ giúp ngăn chặn các yêu cầu giả mạo được gửi từ một website khác đến ứng dụng của người dùng. Trong ứng dụng web truyền thống sử dụng session-based authentication, trình duyệt sẽ tự động gửi cookie phiên trong mỗi request. Điều này tạo điều kiện cho kẻ tấn công lợi dụng trình duyệt của người dùng để gửi các yêu cầu trái phép đến hệ thống. Vì vậy, Spring Security sử dụng CSRF Token để xác minh rằng request thực sự được tạo ra từ ứng dụng hợp lệ.

Đối với REST API sử dụng cơ chế xác thực stateless và token-based như JWT, client thường gửi token thông qua header Authorization thay vì dựa vào cookie phiên. Trình duyệt không tự động gắn các token này vào request nên nguy cơ bị tấn công CSRF thấp hơn nhiều so với ứng dụng web truyền thống. Vì lý do đó, nhiều hệ thống REST API lựa chọn vô hiệu hóa CSRF hoặc sử dụng các cơ chế xác thực khác phù hợp hơn.

Tuy nhiên, việc vô hiệu hóa CSRF một cách mù quáng có thể gây ra những lỗ hổng bảo mật nghiêm trọng. Nếu một ứng dụng web truyền thống vẫn sử dụng session và cookie để xác thực nhưng lại tắt CSRF, kẻ tấn công có thể lợi dụng phiên đăng nhập của người dùng để thực hiện các thao tác trái phép như thay đổi thông tin cá nhân, chuyển tiền hoặc xóa dữ liệu. Do đó, việc vô hiệu hóa CSRF chỉ nên được áp dụng khi hệ thống sử dụng mô hình xác thực phù hợp như REST API stateless với token được truyền qua header.

Phần 2:Thực thi
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/api/auth/register",
                            "/api/auth/login"
                    ).permitAll()
                    .requestMatchers("/api/**").authenticated()
                    .anyRequest().permitAll()
            );

    return http.build();
}