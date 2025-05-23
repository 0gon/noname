import React, { useRef } from "react";
import DefaultLayout from "src/layout/defalutLayout";

const Add = () => {
    // 각 입력 필드에 대한 ref 생성
    const lastNameRef = useRef();
    const firstNameRef = useRef();
    const emailRef = useRef();
    const phoneRef = useRef();
    const hireDateRef = useRef();
    const departmentRef = useRef();
    const jobTitleRef = useRef();
    const salaryRef = useRef();

    const handleSubmit = async (e) => {
        e.preventDefault();

        const data = {
            lastName: lastNameRef.current.value,
            firstName: firstNameRef.current.value,
            email: emailRef.current.value,
            phoneNumber: phoneRef.current.value,
            hireDate: hireDateRef.current.value,
            department: departmentRef.current.value,
            jobTitle: jobTitleRef.current.value,
            salary: salaryRef.current.value
        };

        try {
            const response = await fetch("/api/employees", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(data)
            });

            if (response.ok) {
                alert("등록 성공!");
                e.target.reset(); // 폼 초기화
            } else {
                alert("등록 실패");
            }
        } catch (error) {
            console.error("에러 발생:", error);
            alert("서버 오류");
        }
    };

    return (
        <DefaultLayout>
            <p>◎사원 추가</p>

            <div>
                <form onSubmit={handleSubmit}>
                    <table>
                        <tbody>
                            <tr>
                                <td>이름</td>
                                <td><input type="text" name="lastName" ref={lastNameRef} placeholder="성" required /></td>
                                <td><input type="text" name="firstName" ref={firstNameRef} placeholder="이름" required /></td>
                            </tr>
                            <tr>
                                <td>이메일</td>
                                <td><input type="email" name="email" ref={emailRef} required /></td>
                            </tr>
                            <tr>
                                <td>전화번호</td>
                                <td><input type="tel" name="phoneNumber" ref={phoneRef} required /></td>
                            </tr>
                            <tr>
                                <td>입사일</td>
                                <td><input type="date" name="hireDate" ref={hireDateRef} required /></td>
                            </tr>
                            <tr>
                                <td>소속</td>
                                <td><input type="text" name="department" ref={departmentRef} required /></td>
                            </tr>
                            <tr>
                                <td>직책</td>
                                <td><input type="text" name="jobTitle" ref={jobTitleRef} required /></td>
                            </tr>
                            <tr>
                                <td>연봉</td>
                                <td><input type="number" name="salary" ref={salaryRef} required /></td>
                            </tr>
                        </tbody>
                    </table>

                    <button type="submit">등록</button>
                </form>
            </div>
        </DefaultLayout>
    );
};

export default Add;