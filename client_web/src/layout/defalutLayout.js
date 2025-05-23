import 'src/styles/defaultLayout.css';

const DefaultLayout = ({ children }) => {

  const menu = [
    { title: "조직도", href: "/hrm/organizationChart" },
    { title: "사원추가", href: "/hrm/empl/add" },
  ]


  return (
    <div id='default_layout'>
      <header>
        <div className='logo'>
          <h1><a href='/'>uca</a></h1>
        </div>
        <ul>
          {menu.map((item, index) => (
            <li>
              <a href={item.href}>{item.title}</a>
            </li>
          ))}
        </ul>
      </header>



      <main>
        {children}
      </main>
    </div>
  );
};


export default DefaultLayout;
